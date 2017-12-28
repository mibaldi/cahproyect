import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

interface BaseMvpFragmentView {

    fun getMyFragment(): Fragment
    fun getMyActivity(): AppCompatActivity
}