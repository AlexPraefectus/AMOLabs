import kotlin.math.sqrt

fun main(){
    var sum = 0.0
    var sum2 = 0.0
    var sumInner1 = 0.0
    var sumInner2 = 0.0
    val p=13
    val a=1.0
    val b=3.0
    for (i in 1 .. p){
        for (j in 1 .. p){
            for (k in 1 .. p){
                sum += (i * i * i * j * j * k * sqrt(a + b))
            }
        }
    }
    for (i in 1 .. p){
        sumInner1 = 0.0
        for (j in 1 .. p){
            sumInner2 = 0.0
            for (k in 1 .. p){
                sumInner2 += (i * j * k)
            }
            sumInner1 += (i * j * sumInner2)
            print("Inner2 $sumInner2 Inner1: $sumInner1\n")
        }
        sum2 += i * sumInner1
        print("Inner1: $sumInner1\n")
    }
    sum2 *= sqrt(a + b)
    print("$sum, $sum2\n")
}
