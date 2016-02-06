Change Log
==========

Version 0.3.0 *2016-2-5*
----------------------------

* Uses RxJava's new `fromCallable()` API under the hood now
* Dependency updates
  * RxJava 1.1.0
  * Support libraries 23.1.1
  * Kotlin 1.0.0-rc-1036

Note that there will probably be a patch release when Kotlin goes release 1.0.0. Doing the release
now because rc-1036 introduced some backward-incompatible class file changes.

Version 0.2.0 *2015-11-1*
----------------------------

* Removed `generateAsync` API. Just use normal scheduler selection with your RxJava chains.
* Kotlin extensions only support `Palette.Builder().asObservable` now. Function extensions can't be done as static 
functions on the `Palette` class itself since it has no `companion` object.
* Errors properly propagated via `onError` rather than at creation.
* `onComplete()` properly called when generation is complete.
* Full sample, unit tests, and CI

Version 0.1.0 *2015-10-27*
----------------------------

Initial release.
