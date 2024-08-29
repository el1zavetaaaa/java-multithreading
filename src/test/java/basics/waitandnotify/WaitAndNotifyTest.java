package basics.waitandnotify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class WaitAndNotifyTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testWriterPrepareArticleBeforeEditorWillEditIt() throws InterruptedException {
        Editor editor = new Editor();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(editor);
        executor.execute(new Writer(editor));
        TimeUnit.MILLISECONDS.sleep(1000);

        assertThat(outputStreamCaptor.toString()).isEqualTo("Editor waiting for writer to provide an article\n"
                                                                   + "An article is ready!\n"
                                                                   + "Editor start working on an article\n");
        executor.shutdownNow();
    }
}
