package pl.potera

fun main(args: Array<String>) {
    val t = readInt()
    val results = IntArray(t)
    for (i in 0 until t) {
        val str = readLine()?.split(' ').orEmpty()
        val (n, c) = Pair(str[0].toInt(), str[1].toInt())
        val points = IntArray(n)
        for (j in 0 until n) points[j] = readInt()
        points.sort()
        results[i] = getMaximumDistance(points, c)
    }
    for (result in results) println(result)
}

fun getMaximumDistance(array: IntArray, cows: Int): Int {
    var (start, end) = Pair(1, array[array.lastIndex])
    while (start < end - 1) {
        val middle = (start + end) / 2
        if (checkDistance(array, cows, middle)) {
            start = middle
        } else {
            end = middle - 1
        }
    }
    return if (checkDistance(array, cows, end)) end else start
}

fun checkDistance(array: IntArray, cows: Int, distance: Int): Boolean {
    var (lastPoint, c) = Pair(array[0], cows - 1)
    for (point: Int in array) {
        if (point - lastPoint >= distance) {
            lastPoint = point
            if (--c == 0) return true
        }
    }
    return false
}

fun readInt(): Int = readLine()?.toInt() ?: 0
