Change Log
==========

Version 0.2.0 *(In development)*
----------------------------

* Removed `generateAsync` API. Just use normal scheduler selection with your RxJava chains.
* Kotlin extensions only support `Palette.Builder().asObservable` now. Function extensions can't be done as static 
functions on the `Palette` class itself since it has no `companion` object.
* Errors properly propagated via `onError` rather than at creation.
* `onComplete()` properly called when generation is complete.

Version 0.1.0 *2015-10-27*
----------------------------

Initial release.
