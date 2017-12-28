



open class BaseFragmentPresenter<V : BaseMvpFragmentView> : BaseMvpFragmentPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }


    override fun detachView() {
        mView = null
    }
}