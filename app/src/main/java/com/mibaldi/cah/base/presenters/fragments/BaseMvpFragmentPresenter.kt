
interface BaseMvpFragmentPresenter<in V : BaseMvpFragmentView> {

    fun attachView(view: V)

    fun detachView()
}