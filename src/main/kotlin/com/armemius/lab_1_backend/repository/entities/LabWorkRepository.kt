import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class LabWork(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String,
//    @Embedded
//    val coordinates: Coordinates,
//    val creationDate: LocalDate,
//    val description: String,
//    @Enumerated(EnumType.STRING)
//    val difficulty: Difficulty,
//    @Embedded
//    val discipline: Discipline,
//    val minimalPoint: Float,
//    val tunedInWorks: Int,
//    @Embedded
//    val author: Person
)