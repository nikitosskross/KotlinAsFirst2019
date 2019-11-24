@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import lesson2.task1.bishopThreatens
import lesson2.task1.rookThreatens
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    var n1 = number / 1000
    var n2 = number / 100 % 10
    var n3 = number % 100 / 10
    var n4 = number % 10
    return n1 + n2 == n3 + n4
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return rookThreatens(x1, y1, x2, y2) || bishopThreatens(x1, y1, x2, y2)
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */

fun equalsToOneOf(n: Int, string: String): Boolean {
    for (s in string.split(",")) {
        if (s.equals(n.toString())) return true
    }
    return false
}

fun daysInMonth(month: Int, year: Int): Int {
    return when {
        month == 2 -> if (year % 4 == 0) 29 else 28
        equalsToOneOf(month, "4,6,9,11") -> 30
        else -> 31
    }
}
/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = sqrt(sqr(x1 - x2) + sqr(y1 - y2)) + r1 <= r2

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean =
    ((r >= a) && (s >= b)) ||
            ((r >= b) && (s >= a)) ||
            ((r >= a) && (s >= c)) ||
            ((r >= c) && (s >= a)) ||
            ((r >= c) && (s >= b)) ||
            ((r >= b) && (s >= c))
