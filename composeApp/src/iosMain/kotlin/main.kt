import androidx.compose.ui.window.ComposeUIViewController
import com.bekircaglar.monochatui.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
