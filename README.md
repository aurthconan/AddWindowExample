# AddWindowExample
Only A example for a merge request https://github.com/dtmilano/AndroidViewClient/pull/135

In this example, MainActivity add a new window via WindowManager.addView, this window is visible but not focusable.
[This commit](https://github.com/dtmilano/AndroidViewClient/commit/2e68c70786a8de536ee0ddb2cc04f5105044ae55) add a window id to view, so view can compute the right coordinate even if it is in a non-focusable window.
And script like the following can works.

```python
#! /usr/bin/env python

from com.dtmilano.android.viewclient import ViewClient

kwargs1 = {'verbose': True, 'ignoresecuredevice': True}
kwargs2 = {'startviewserver': True, 'forceviewserveruse': True, 'autodump': False, 'ignoreuiautomatorkilled': True}
vc = ViewClient(*ViewClient.connectToDeviceOrExit(**kwargs1), **kwargs2)

#vc.device.startActivity(component="individual.aurthconan.addwindowexample/individual.aurthconan.addwindowexample.MainActivity")

vc.dump("individual.aurthconan.addwindowexample.floatingwindow")
boom = vc.findViewWithText("BOOM!")
boom.touch()
```
