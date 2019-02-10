package tech.daniellas.p1;

import java.util.function.BiFunction;
import java.util.function.Function;

class Currying {
// Function taking two arguments
static BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

// This is how we call two arguments function
Integer sumRes = sum.apply(1, 2);

// Curried
static Function<Integer, Function<Integer, Integer>> sumCurr = a -> b -> a + b;

// And this is how we call curried function
Integer sumCurrRes = sumCurr.apply(1).apply(2);
}