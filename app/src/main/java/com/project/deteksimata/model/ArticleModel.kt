
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val id: Int,
    val title: String,
    val content: String,
    val img : String,
) : Parcelable

val articleList: ArrayList<ArticleModel> = arrayListOf(
    ArticleModel(
        id = 1,
        title = "Katarak",
        content = "Katarak adalah kondisi di mana lensa mata menjadi keruh, yang menyebabkan penglihatan kabur atau buram. Penyebab utamanya adalah proses penuaan, namun juga bisa dipicu oleh cedera mata atau penyakit tertentu.",
        img = "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/06/23030719/Katarak.jpg"
    ),
    ArticleModel(
        id = 2,
        title = "Glaukoma",
        content = "Glaukoma adalah penyakit yang merusak saraf optik mata dan dapat menyebabkan kehilangan penglihatan permanen jika tidak segera diobati. Glaukoma sering dikaitkan dengan peningkatan tekanan di dalam mata.",
        img = "https://d324bm9stwnv8c.cloudfront.net/Glaukoma_Bisa_Sebabkan_Kebutaan,_Atasi_Segera.jpg"
    ),
    ArticleModel(
        id = 3,
        title = "Konjungtivitis",
        content = "Konjungtivitis ini terjadi akibat adanya peradangan yang membuat mata merah, perih, gatal, dan berair. Konjungtivitis bisa disebabkan oleh alergi atau iritasi pada mata. Namun, bisa juga disebabkan oleh infeksi bakteri yang membuat konjungtiva mengeluarkan nanah.",
        img = "https://res.cloudinary.com/dk0z4ums3/image/upload/v1656999099/attached_image/konjungtivitis-0-alodokter.jpg"
    ),
)