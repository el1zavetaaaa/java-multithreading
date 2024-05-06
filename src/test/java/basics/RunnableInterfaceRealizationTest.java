package basics;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunnableInterfaceRealizationTest {
    @Spy
    static LiftOff launchAfter5 = new LiftOff(5);
    @Spy
    static LiftOff launchAfter3 = new LiftOff(3);
    @Spy
    static LiftOff launchAfter10 = new LiftOff(10);
    @Spy
    static FibonacciConcurrent fibonacciConcurrent18 = new FibonacciConcurrent(18);
    @Spy
    static FibonacciConcurrent fibonacciConcurrent5 = new FibonacciConcurrent(5);
    @Spy
    static FibonacciConcurrent fibonacciConcurrent10 = new FibonacciConcurrent(10);

    final static List<Integer> arrayList18 = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584);
    final static List<Integer> arrayList5 = List.of(1, 1, 2, 3, 5);
    final static List<Integer> arrayList10 = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55);

    private static Stream<Arguments> launchAfterSecondsConfigurationForSingleThread() {
        return Stream.of(Arguments.of(5, launchAfter5),
                Arguments.of(3, launchAfter3),
                Arguments.of(10, launchAfter10));
    }

    private static Stream<Arguments> launchAfterSecondsConfigurationForMultipleThreads() {
        return Stream.of(Arguments.of(5, launchAfter5,
                3, launchAfter3,
                10, launchAfter10));
    }

    private static Stream<Arguments> testFibonacciConfigurationForSingleThread() {
        return Stream.of(Arguments.of(arrayList18, fibonacciConcurrent18),
                Arguments.of(arrayList5, fibonacciConcurrent5),
                Arguments.of(arrayList10, fibonacciConcurrent10));
    }

    private static Stream<Arguments> testFibonacciConfigurationForMultipleThreads() {
        return Stream.of(Arguments.of(arrayList5, fibonacciConcurrent5,
                arrayList10, fibonacciConcurrent10,
                arrayList18, fibonacciConcurrent18));
    }


    @ParameterizedTest
    @MethodSource("launchAfterSecondsConfigurationForSingleThread")
    public void testRunnableInterfaceRealizationWith1Thread(final int interactionsCount, final LiftOff launch) throws InterruptedException {
        Thread test = new Thread(launch);
        test.start();
        test.join();
        assertEquals(interactionsCount, launch.getInteractionCount());
    }

    @ParameterizedTest
    @MethodSource("launchAfterSecondsConfigurationForMultipleThreads")
    public void testRunnableInterfaceRealizationWithMultipleThreads(final int interactionsCount1,
                                                                    final LiftOff launch1,
                                                                    final int interactionsCount2,
                                                                    final LiftOff launch2,
                                                                    final int interactionsCount3,
                                                                    final LiftOff launch3) throws InterruptedException {
        Thread thread1 = createNewThreadWithLaunchAfterSeconds(launch1);
        Thread thread2 = createNewThreadWithLaunchAfterSeconds(launch2);
        Thread thread3 = createNewThreadWithLaunchAfterSeconds(launch3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(interactionsCount1, launch1.getInteractionCount());
        assertEquals(interactionsCount2, launch2.getInteractionCount());
        assertEquals(interactionsCount3, launch3.getInteractionCount());
    }

    private Thread createNewThreadWithLaunchAfterSeconds(final LiftOff launchAfterSeconds) {
        return new Thread(launchAfterSeconds);
    }

    @ParameterizedTest
    @MethodSource("testFibonacciConfigurationForSingleThread")
    public void testFibonacci1Thread(final List<Integer> arrayList, final FibonacciConcurrent fibonacciConcurrent) throws InterruptedException {
        Thread thread = new Thread(fibonacciConcurrent);

        thread.start();
        thread.join();

        assertEquals(arrayList, fibonacciConcurrent.getFibonacciSequence());

        fibonacciConcurrent.clean();
    }

    @ParameterizedTest
    @MethodSource("testFibonacciConfigurationForMultipleThreads")
    public void testFibonacciMultipleThreads(final List<Integer> list1,
                                             final FibonacciConcurrent fibonacci1,
                                             final List<Integer> list2,
                                             final FibonacciConcurrent fibonacci2,
                                             final List<Integer> list3,
                                             final FibonacciConcurrent fibonacci3) throws InterruptedException {
        Thread thread1 = new Thread(fibonacci1);
        Thread thread2 = new Thread(fibonacci2);
        Thread thread3 = new Thread(fibonacci3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(list1, fibonacci1.getFibonacciSequence());
        assertEquals(list2, fibonacci2.getFibonacciSequence());
        assertEquals(list3, fibonacci3.getFibonacciSequence());

        fibonacci1.clean();
        fibonacci2.clean();
        fibonacci3.clean();
    }

}
