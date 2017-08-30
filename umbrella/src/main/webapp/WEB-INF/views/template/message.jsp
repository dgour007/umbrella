<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
</head>
<body>
	<c:set var="head" value="${heading}" scope="session" />
	<c:set var="crumbs" value="${crumb}" scope="session" />

	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-header row">
				<c:if test="${head != null}">
					<div class="content-header-left col-md-6 col-xs-12 mb-0">
						<h2 class="content-header-title">
							<spring:message code="${head}" />
						</h2>
					</div>
					<div
						class="content-header-right breadcrumbs-right breadcrumbs-top col-md-6 col-xs-12">
						<div class="breadcrumb-wrapper col-xs-12">
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a class="deep-orange" href="/umbrella/home">Home</a></li>
								<c:forEach items="${crumbs}" var="crumb">
									<li class="breadcrumb-item"><a class="deep-orange" href="#"><spring:message
											code="${crumb}" /></a></li>
								</c:forEach>
							</ol>
						</div>
					</div>
				</c:if>
			</div>
			<c:if
				test="${messages.hasSuccess || messages.hasWarning || messages.hasError || messages.hasInfo || messages.hasPrimary}">

				<div class="row">
					<div class="col-md-12">
						<c:if test="${messages.hasSuccess}">
							<c:forEach items="${messages.success}" var="success">
								<div class="alert alert-success alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Well done!</strong> ${success}
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${messages.hasWarning}">
							<c:forEach items="${messages.warnings}" var="warning">
								<div class="alert alert-warning alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Warning!</strong> ${warning}
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${messages.hasError}">
							<c:forEach items="${messages.errors}" var="error">
								<div class="alert alert-danger alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Oh snap!</strong> ${error}
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${messages.hasInfo}">
							<c:forEach items="${messages.infos}" var="info">
								<div class="alert alert-info alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Heads up!</strong> ${info}
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${messages.hasPrimary}">
							<c:forEach items="${messages.primaries}" var="primary">
								<div class="alert alert-primary alert-dismissible fade in"
									role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									${primary}
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>