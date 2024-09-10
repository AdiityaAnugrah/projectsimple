
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val id: Int,
    val title: String,
    val content: String,
    val img : String,
) : Parcelable
