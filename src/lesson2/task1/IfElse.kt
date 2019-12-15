@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var string = age.toString()
    val cond = (age / 10) % 10 != 1
    string += when {
        age % 10 == 1 && cond -> " год"
        age % 10 in 2..4 && cond -> " года"
        else -> " лет"
    }
    return string
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val halfWay = (s1 + s2 + s3) / 2
    return when {
        halfWay <= s1 -> halfWay / v1
        (halfWay > s1) && (halfWay <= s1 + s2) -> t1 + (halfWay - s1) / v2
        else -> t1 + t2 + (halfWay - s1 - s2) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val threatens1 = rookX1 == kingX || rookY1 == kingY
    val threatens2 = rookX2 == kingX || rookY2 == kingY
    return when {
        threatens1 && threatens2 -> 3
        threatens1 -> 1
        threatens2 -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */

fun rookThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = x1 == x2 || y1 == y2

fun bishopThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = abs(x1 - x2) == abs(y1 - y2)

fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val threatensBishop = bishopThreatens(bishopX, bishopY, kingX, kingY)
    val threatensRook = rookThreatens(rookX, rookY, kingX, kingY)
    val bishopIntersectRook = bishopThreatens(bishopX, bishopY, rookX, rookY) ||
            rookThreatens(rookX, rookY, bishopX, bishopY)
    return when {
        threatensBishop && threatensRook && !bishopIntersectRook -> 3
        threatensRook && !bishopIntersectRook -> 1
        threatensBishop && !bishopIntersectRook -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var a1: Double = a
    var b1: Double = b
    var c1: Double = c
    if (a > b) {
        if (a > c) {
            c1 = a
            a1 = c
            b1 = b
        }
    } else if (b > a) {
        if (b > c) {
            c1 = b
            a1 = a
            b1 = c
        }
    }
    val cos = (sqr(a1) + sqr(b1) - sqr(c1)) / (2 * a1 * b1)
    return when {
        (c >= (a + b)) || (b >= (a + c)) || (a >= (b + c)) -> -1
        cos > 0.0 -> 0
        cos < 0.0 -> 2
        else -> 1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val intersection = min(b, d) - max(a, c)
    if (intersection < 0) return -1
    return intersection
}
