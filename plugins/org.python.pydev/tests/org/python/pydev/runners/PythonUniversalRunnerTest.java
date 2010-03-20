package org.python.pydev.runners;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.python.pydev.core.IPythonNature;
import org.python.pydev.core.TestDependent;
import org.python.pydev.core.Tuple;
import org.python.pydev.editor.codecompletion.revisited.CodeCompletionTestsBase;
import org.python.pydev.editor.codecompletion.revisited.modules.CompiledModule;
import org.python.pydev.runners.UniversalRunner.AbstractRunner;

public class PythonUniversalRunnerTest extends CodeCompletionTestsBase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PythonUniversalRunnerTest.class);
    }

    
    @Override
    public void setUp() throws Exception {
    	super.setUp();
        CompiledModule.COMPILED_MODULES_ENABLED = true;
        this.restorePythonPath(TestDependent.IRONPYTHON_LIB, false);
    }

    @Override
    public void tearDown() throws Exception {
        CompiledModule.COMPILED_MODULES_ENABLED = false;
        super.tearDown();
    }
    
    public void testUniversalRunnerWithJython() throws Exception {
		AbstractRunner runner = UniversalRunner.getRunner(nature);
		assertEquals(nature.getInterpreterType(), IPythonNature.INTERPRETER_TYPE_PYTHON);
		Tuple<String, String> output = runner.runCodeAndGetOutput(
				"import sys\nprint 'test'\nprint >> sys.stderr, 'err'", null, null, new NullProgressMonitor());
		assertEquals("test", output.o1.trim());
		assertEquals("err", output.o2.trim());
		
		Tuple<Process, String> createProcess = 
			runner.createProcess(TestDependent.TEST_PYSRC_LOC+"universal_runner_test.py", null, null, new NullProgressMonitor());
		output = SimpleRunner.getProcessOutput(createProcess.o1, "", new NullProgressMonitor());
		assertEquals("stdout", output.o1.trim());
		assertEquals("stderr", output.o2.trim());
	}
    
}
