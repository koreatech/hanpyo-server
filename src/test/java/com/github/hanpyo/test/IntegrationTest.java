package com.github.hanpyo.test;

import static com.github.hanpyo.entity.QMember.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.github.hanpyo.HanpyoApplication;
import com.github.hanpyo.entity.Member;
import com.github.hanpyo.entity.MemberRole;
import com.graphql.spring.boot.test.GraphQLTestAutoConfiguration;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Transactional
@SpringBootTest(
	classes = {HanpyoApplication.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import({
	JpaTestSupport.class,
	GraphQLTestAutoConfiguration.class
})
@ActiveProfiles("test")
@Disabled
public abstract class IntegrationTest {

	@Autowired
	protected GraphQLTestTemplate graphQLTestTemplate;

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

	@Autowired
	protected JpaTestSupport jpaTestSupport;

	protected Member principal = Member.builder()
		.email("youngxpepp@gmail.com")
		.name("geonhonglee")
		.nickname("youngxpepp")
		.grade(1)
		.major("CSE")
		.role(MemberRole.VERIFIED_MEMBER)
		.build();

	@BeforeEach
	public void savePrincipal() {
		jpaTestSupport.save(principal);
	}

	@AfterEach
	public void deleteAll() {
		jpaTestSupport.deleteAll(member);
	}
}
