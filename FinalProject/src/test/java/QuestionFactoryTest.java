import org.example.finalproject.model.LongQuestion;
import org.example.finalproject.model.McQuestion;
import org.example.finalproject.model.Question;
import org.example.finalproject.model.TfQuestion;
import org.example.finalproject.model.factory.QuestionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class QuestionFactoryTest {

    @Test
    void testCreateMcQuestion() {
        Question question = QuestionFactory.createQuestion(
                "mc",
                "What is the capital of France?",
                'B',
                "A. Berlin", "B. Paris", "C. Madrid"
        );
        Assertions.assertInstanceOf(McQuestion.class, question);
        McQuestion mcQuestion = (McQuestion) question;
        Assertions.assertEquals("What is the capital of France?", mcQuestion.getQuestion());
        Assertions.assertEquals('B', mcQuestion.getAnswer());
        Assertions.assertArrayEquals(new String[]{"A. Berlin", "B. Paris", "C. Madrid"}, mcQuestion.getOptions());
    }

    @Test
    void testCreateTfQuestion() {
        Question question = QuestionFactory.createQuestion(
                "tf",
                "Is the sky blue?",
                true
        );
        Assertions.assertInstanceOf(TfQuestion.class, question);
        TfQuestion tfQuestion = (TfQuestion) question;
        Assertions.assertEquals("Is the sky blue?", tfQuestion.getQuestion());
        Assertions.assertTrue(tfQuestion.isAnswer());
    }

    @Test
    void testCreateLongQuestion() {
        Question question = QuestionFactory.createQuestion(
                "long",
                "Explain polymorphism in OOP.",
                "Polymorphism allows objects to be treated as instances of their parent class."
        );
        Assertions.assertTrue(question instanceof LongQuestion);
        LongQuestion longQuestion = (LongQuestion) question;
        Assertions.assertEquals("Explain polymorphism in OOP.", longQuestion.getQuestion());
        Assertions.assertEquals("Polymorphism allows objects to be treated as instances of their parent class.", longQuestion.getAnswer());
    }

    @Test
    void testInvalidQuestionType() {
        Assertions.assertThrows(InvalidParameterException.class, () ->
                QuestionFactory.createQuestion("unknown", "Invalid question?", null)
        );
    }
}
