/*		
 *		Copyright (c) 2015. 
 *		Johannes Bauer, Fabian Buske, Matthias Fisch,
 *		Michael Mitterer, Maximilian Witzelsperger
 *
 *		Licensed under the Apache License, Version 2.0 (the "License");
 *		you may not use this file except in compliance with the License.
 *		You may obtain a copy of the License at
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *		Unless required by applicable law or agreed to in writing, software
 *		distributed under the License is distributed on an "AS IS" BASIS,
 *		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *		See the License for the specific language governing permissions and
 *		limitations under the License.
 */
package sep.gaia.resources;

import java.util.Collection;

/**
 * Interface accepted by <code>Loader</code> for splitting a query into pieces in
 * order to process them asynchronously.
 * @author Matthias Fisch
 *
 * @param <Q> The type of queries to be split.
 */
public interface QuerySplitter<Q> {

	/**
	 * Splits the <code>query</code> into smaller parts that can be processed in separate threads.
	 * Used by <code>Loader</code> when splitting a bigger query into parts and delegating processing
	 * to workers.
	 * @return Collection of query-packages forming the <code>query</code> in union.
	 */
	public Collection<Q> splitQuery(Q query);
}
