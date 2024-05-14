package basics.callable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static basics.utility.MultiThreadingBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallableInterfaceRealizationTest {

    @Spy
    private static FibonacciCallable fibonacciCallable18 = new FibonacciCallable(18);
    @Spy
    private static FibonacciCallable fibonacciCallable5 = new FibonacciCallable(5);
    @Spy
    private static FibonacciCallable fibonacciCallable10 = new FibonacciCallable(10);


    private static Stream<Arguments> testFibonacciConfigurationForCallableRealization() {
        return Stream.of(Arguments.of(arrayList18, fibonacciCallable18),
                Arguments.of(arrayList5, fibonacciCallable5),
                Arguments.of(arrayList10, fibonacciCallable10));
    }

    private static Stream<Arguments> testConfigurationForExecutionOfCallableInterfaceInsideAMethod() {
        return Stream.of(Arguments.of(18, arrayList18),
                Arguments.of(5, arrayList5),
                Arguments.of(10, arrayList10));
    }

    @ParameterizedTest
    @MethodSource("testFibonacciConfigurationForCallableRealization")
    public void fibonacciCallableShouldReturnSequence(final List<Integer> result, final FibonacciCallable fibonacciCallable) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<ArrayList<Integer>> executionResult = exec.submit(fibonacciCallable);

        try {
            assertEquals(result, executionResult.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("testConfigurationForExecutionOfCallableInterfaceInsideAMethod")
    public void testCallableCreationInsideMethod(final int quantity, final List<Integer> result) {
        Future<ArrayList<Integer>> executionResult = new CallableMethod().callTask(quantity);
        try {
            assertEquals(result, executionResult.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
