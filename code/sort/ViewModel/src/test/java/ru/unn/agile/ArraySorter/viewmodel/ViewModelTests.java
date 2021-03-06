package ru.unn.agile.ArraySorter.viewmodel;

import ru.unn.agile.ArraySorter.viewmodel.ViewModel.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    public void setViewModel(final ViewModel vM) {
        this.viewModel = vM;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getElemArray());
        assertEquals("", viewModel.getSortedArrayStringRepresentation());
        assertEquals("", viewModel.getInputArrayStringRepresentation());
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void canAddOneElementToArray() {
        viewModel.setInputValue("3.0");
        viewModel.addProcess();

        assertEquals("[3.0]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canAddSeveralElementsToArray() {
        viewModel.setInputValue("-5.0");
        viewModel.addProcess();
        viewModel.setInputValue("4.0");
        viewModel.addProcess();
        viewModel.setInputValue("2.75");
        viewModel.addProcess();

        assertEquals("[-5.0, 4.0, 2.75]", viewModel.getInputArrayStringRepresentation());
    }

    @Test
    public void canClearArray() {
        viewModel.setInputValue("6");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("10");
        viewModel.addProcess();
        viewModel.setInputValue("8");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals("[]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  canSortOfArrayWithOneElement() {
        viewModel.setInputValue("-4.0");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4.0]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  canSortOfNonSortedBigArray() {
        viewModel.setInputValue("-4.0");
        viewModel.addProcess();
        viewModel.setInputValue("-0.4");
        viewModel.addProcess();
        viewModel.setInputValue("3.1");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals("[-4.0, -0.4, 3.1]", viewModel.getSortedArrayStringRepresentation());
    }

    @Test
    public void  isWaitingStatusWhenLaunch() {
        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStatWhenBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isWaitingStateWhenAddAndDelElemEmptyField() {
        viewModel.setInputValue("");

        viewModel.processingAddField();

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test
    public void isReadyStateWhenAddElemFieldIsWriteIn() {
        viewModel.setInputValue("6.1");

        viewModel.processingAddField();

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setInputValue("b");

        viewModel.processingAddField();

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.setInputValue("-1.0");
        viewModel.addProcess();
        viewModel.setInputValue("2.0");
        viewModel.addProcess();
        viewModel.setInputValue("5.4");

        viewModel.sort();

        assertEquals(Status.SUCCESSFUL, viewModel.getCurrentState());
    }

    @Test
    public void isAddButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenLaunch() {
        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isAddButtonEnabledAddElemFieldIsCorrect() {
        viewModel.setInputValue("1.2");
        viewModel.processingAddField();

        assertEquals(true, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddElemFieldIsEmpty() {
        viewModel.setInputValue("");
        viewModel.processingAddField();

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isAddButtonDisabledWhenAddElemIsInvalid() {
        viewModel.setInputValue("ijijfdf");
        viewModel.processingAddField();

        assertEquals(false, viewModel.isAddButtonEnabled());
    }

    @Test
    public void isClearButtonEnabledWhenArrayIsNotEmpty() {
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("9");
        viewModel.addProcess();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWhenArrayIsNotEmpty() {
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();

        assertEquals(true, viewModel.isSortButtonEnabled());
    }
    @Test
    public void isClearButtonDisabledWhenClearArray() {
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("4");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isClearButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenClearArray() {
        viewModel.setInputValue("8");
        viewModel.addProcess();
        viewModel.setInputValue("2");
        viewModel.addProcess();
        viewModel.setInputValue("1");
        viewModel.addProcess();
        viewModel.setInputValue("5");
        viewModel.addProcess();

        viewModel.clearProcess();

        assertEquals(false, viewModel.isSortButtonEnabled());
    }

    @Test
    public void isClearButtonAddOneElementEnabled() {
        viewModel.setInputValue("2");
        viewModel.addProcess();

        viewModel.sort();

        assertEquals(true, viewModel.isClearButtonEnabled());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsCorrect() {
        viewModel.setInputValue("test");
        viewModel.processingAddField();
        viewModel.setInputValue("12.1");
        viewModel.processingAddField();

        assertEquals(Status.READY, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsInvalid() {
        viewModel.setInputValue("12.1");
        viewModel.processingAddField();
        viewModel.setInputValue("test");
        viewModel.processingAddField();

        assertEquals(Status.BAD_FORMAT, viewModel.getCurrentState());
    }

    @Test
    public void canChangeStateIfAddElemFieldIsEmpty() {
        viewModel.setInputValue("12.1");
        viewModel.processingAddField();
        viewModel.setInputValue("");
        viewModel.processingAddField();

        assertEquals(Status.WAITING, viewModel.getCurrentState());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsViewModelWhenLoggerIsNull() {
        FakeLogger logger = null;

        new ViewModel(logger);
    }

    @Test
    public void canConstructViewModelWhenLoggerIsNotNull() {
        FakeLogger logger = new FakeLogger();

        new ViewModel(logger);
    }

    @Test
    public void isLoggerEmptyWhenStartup() {
        final int expected = 0;
        List<String> log = viewModel.getLog();

        assertEquals(expected, log.size());
    }

    @Test
    public void isLogUpdatedWhenAddToArray() {
        viewModel.setInputValue("10");

        viewModel.addProcess();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.ADD_LOG + viewModel.getElemArray() + ".*"));
    }

    @Test
    public void isLogUpdatedWhenClearArray() {
        viewModel.clearProcess();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ViewModel.CLEAR_LOG + ".*"));
    }

    @Test
    public void isLogUpdatedWhenSortArray() {
        viewModel.setInputValue("10");
        viewModel.addProcess();
        viewModel.setInputValue("-3.4");
        viewModel.addProcess();
        viewModel.setInputValue("9");
        viewModel.addProcess();

        viewModel.sort();

        List<String> log = viewModel.getLog();
        String message = log.get(log.size() - 1);
        assertTrue(message.matches(".*" + viewModel.getSortedArrayStringRepresentation() + ".*"));
    }

    @Test
    public void isLogUpdatedWhenNewInputElemHasBadFormat() {
        viewModel.setInputValue("hello");
        viewModel.processingAddField();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + Status.BAD_FORMAT + ".*"));
    }
}
