package com.intellect.batch.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intellect.batch.LaunchApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=LaunchApplication.class)
public class AbstractBaseTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractBaseTest.class);

}
