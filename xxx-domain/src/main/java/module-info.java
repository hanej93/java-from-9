module com.domain {
	exports org.domain;
	exports org.domain.service;
	// 특정 패키지만 어떤 모듈에게 허용
	// exports org.domain to com.api;
	
	// deep reflection 특정 패키지만 허용
	opens org.domain;

	// 해당 레포지토리에 대해 두개의 구현체를 등록
	provides org.domain.service.StringRepository with
		org.domain.service.MemoryStringRepository,
		org.domain.service.DatabaseStringRepository;
}