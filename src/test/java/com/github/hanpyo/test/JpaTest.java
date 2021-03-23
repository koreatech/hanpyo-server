package com.github.hanpyo.test;

import com.github.hanpyo.config.JpaConfig;
import com.github.hanpyo.config.QuerydslConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import({JpaConfig.class, QuerydslConfig.class, JpaTestSupport.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Disabled
public abstract class JpaTest {

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

	@Autowired
	protected JpaTestSupport jpaTestSupport;
}
