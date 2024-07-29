package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FileCompareTest extends TestCase {
    public FileCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FileCompareTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSameFiles() {
        String file1 = "/home/prateek/Documents/filename1.txt";
        String file2 = "/home/prateek/Documents/filename2.txt";
        FileCompare.compareFiles(file1, file2);
    }

}
