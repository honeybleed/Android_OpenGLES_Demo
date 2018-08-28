package asiainnovations.com.opengles_demo.fragments

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseGLFragment : Fragment(), GLSurfaceView.Renderer {
    var glSurfaceView: GLSurfaceView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        glSurfaceView = createGLSurfaveView()
        return glSurfaceView
    }

    protected open fun createGLSurfaveView(): GLSurfaceView =
            GLSurfaceView(context!!).apply {
                setPreserveEGLContextOnPause(true)
                setEGLContextClientVersion(getEglContextClientVersion())
                setRenderer(this@BaseGLFragment)
                setRenderMode(getRenderMode())
            }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)
            glSurfaceView?.requestRender()
    }

    protected open fun getEglContextClientVersion() = 2
    protected open fun getRenderMode() = GLSurfaceView.RENDERMODE_WHEN_DIRTY
}