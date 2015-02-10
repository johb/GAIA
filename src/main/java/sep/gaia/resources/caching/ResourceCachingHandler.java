package sep.gaia.resources.caching;

import sep.gaia.resources.DataResource;

/**
 * This event handler should be used for caching purposes where the cached
 * resources contain members not implementing {@link java.io.Serializable} and
 * thus must be treated separately. The respective member should be marked with
 * the <code>transient</code> keyword in order to prevent serialization errors.
 * This preprocessor may also be used when the cached resources have members that
 * allocate memory via JNI and thus are not affected by GC.
 */
public interface ResourceCachingHandler<R extends DataResource> {

    /**
     * Called by the {@link AdvancedCache} before the caching of the
     * resource is done.
     * @param resource The resource that will be cached shortly.
     * @return Returns true if the given resource should be dumped. If this method returns false,
     * the cache will remove this resource from its index before dumping.
     */
    public boolean handleResourceDump(R resource);

    /**
     * Called by the {@link AdvancedCache} after the reading from dump of the
     * resource is done.
     * @param resource The resource that was read.
     * @return Returns true if the given resource should be kept in cache. If this method returns false,
     * the cache will remove this resource from its index (e.g. because of error).
     */
    public boolean handleResourceRead(R resource);

    /**
     * Called by the {@link AdvancedCache} before the resource is removed from cache
     * by the caches {@link CacheRemovalStrategy}.
     * This is useful if the resources have allocated memory via JNI that must be freed separately.
     * @param resource
     */
    public void handleResourcePurged(R resource);
}