package com.github.hanpyo.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.github.hanpyo.HanpyoApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {HanpyoApplication.class})
@Import({JpaTestSupport.class})
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@Disabled
public abstract class IntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

	@Autowired
	protected JpaTestSupport jpaTestSupport;
}
