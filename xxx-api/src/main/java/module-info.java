module com.api {
	requires com.domain;
	// requires transitive com.domain;

	// 해당 서비스를 사용하겠다.
	uses org.domain.service.StringRepository;
}