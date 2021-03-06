# GAIA
GAIA was developed during the software engineering lab at the University of Passau by 5 students.
GAIA is showing the whole world in 3d and after zooming in, the world can be discovered with maps from the OpenStreetMap Project (http://www.openstreetmap.org/).
It is possible to search for locations, set markers on visited places, select points of interests from various categories and to show current weather information. The user can extract map areas that suit his needs with all selected additional information overlays.

The rendering of the globe and map tiles was down with the Java OpenGL (JOGL) binding and the application was written in Java and Swing.
The APIs used for collecting the additional informations are:
* the Overpass API for collecting points of interests (http://wiki.openstreetmap.org/wiki/Overpass_API)
* Nominatim API for searching for locations (http://www.nominatim.org/)
* Weather data comes from the OpenWeatherMap Project (http://openweathermap.org/)

# Screenshots
![Alt text](gaia-3d.png "GAIA in 3d mode")

![Alt text](gaia-2d.png "GAIA in 2d mode")

![Alt text](gaia-2d-2.png "GAIA in 2d mode")
