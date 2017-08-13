<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>

<html lang="en" data-textdirection="ltr" class="loading">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<link rel="apple-touch-icon" sizes="60x60"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/apple-icon-60.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/apple-icon-76.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/apple-icon-120.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/apple-icon-152.png">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/favicon.ico">
<link rel="shortcut icon" type="image/png"
	href="<%=request.getContextPath()%>/static/app-assets/images/ico/favicon-32.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<!-- BEGIN VENDOR CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/bootstrap.css">
<!-- font icons-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/fonts/icomoon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/fonts/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/vendors/css/extensions/pace.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/vendors/css/ui/prism.min.css">
<!-- END VENDOR CSS-->
<!-- BEGIN ROBUST CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/bootstrap-extended.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/app.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/colors.css">
<!-- END ROBUST CSS-->
<!-- BEGIN Page Level CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/core/menu/menu-types/vertical-menu.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/core/menu/menu-types/vertical-overlay-menu.css">
<!-- END Page Level CSS-->
<!-- BEGIN Custom CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/assets/css/style.css">
</head>
<!-- END Custom CSS-->
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns  fixed-navbar">
	
	<c:set var="fullname" value="${user.getFullName()}" scope="session"/>
	<c:set var="theme" value="${user.getTheme()}" scope="session"/>
	<c:set var="appList" value="${user.getAppList()}" scope="session"/>
	
	<!-- navbar-fixed-top-->
	<nav
		class="header-navbar navbar navbar-with-menu navbar-fixed-top navbar-${theme} navbar-shadow">
		<div class="navbar-wrapper">
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<li class="nav-item mobile-menu hidden-md-up float-xs-left"><a
						class="nav-link nav-menu-main menu-toggle hidden-xs"><i
							class="icon-menu5 font-large-1"></i></a></li>
					<c:if test="${theme=='dark'}">
						<li class="nav-item"><a href="#" class="navbar-brand nav-link"><img
								alt="branding logo"
								src="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-light.png"
								data-expand="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-light.png"
								data-collapse="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-small.png"
								class="brand-logo"></a></li>
					</c:if>
					<c:if test="${theme=='light'}">
						<li class="nav-item"><a href="#" class="navbar-brand nav-link"><img
								alt="branding logo"
								src="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-dark.png"
								data-expand="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-dark.png"
								data-collapse="<%=request.getContextPath()%>/static/app-assets/images/logo/robust-logo-small.png"
								class="brand-logo"></a></li>
					</c:if>
					<li class="nav-item hidden-md-up float-xs-right"><a
						data-toggle="collapse" data-target="#navbar-mobile"
						class="nav-link open-navbar-container"><i
							class="icon-ellipsis pe-2x icon-icon-rotate-right-right"></i></a></li>
				</ul>
			</div>
			<div class="navbar-container content container-fluid">
				<div id="navbar-mobile" class="collapse navbar-toggleable-sm">
					<ul class="nav navbar-nav">
						<li class="nav-item hidden-sm-down"><a
							class="nav-link nav-menu-main menu-toggle hidden-xs"><i
								class="icon-menu5"> </i></a></li>
						<li class="nav-item hidden-sm-down"><a href="#"
							class="nav-link nav-link-expand"><i
								class="ficon icon-expand2"></i></a></li>
					</ul>
					<ul class="nav navbar-nav float-xs-right">
						
						<li class="dropdown dropdown-language nav-item"><a
							id="dropdown-flag" href="#" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							class="dropdown-toggle nav-link"><i
								class="flag-icon flag-icon-gb"></i><span
								class="selected-language">English</span></a>
							<div aria-labelledby="dropdown-flag" class="dropdown-menu">
								<a href="#" class="dropdown-item"><i
									class="flag-icon flag-icon-gb"></i> English</a><a href="#"
									class="dropdown-item"><i class="flag-icon flag-icon-om"></i>
									Arabic</a>
							</div></li>

						<li class="dropdown dropdown-notification nav-item"><a
							href="#" data-toggle="dropdown" class="nav-link nav-link-label"><i
								class="ficon icon-bell4"></i><span
								class="tag tag-pill tag-default tag-danger tag-default tag-up">1</span></a>
							<ul class="dropdown-menu dropdown-menu-media dropdown-menu-right">
								<li class="dropdown-menu-header">
									<h6 class="dropdown-header m-0">
										<span class="grey darken-2">Notifications</span><span
											class="notification-tag tag tag-default tag-danger float-xs-right m-0">1
											New</span>
									</h6>
								</li>
								<li class="list-group scrollable-container"><a
									href="javascript:void(0)" class="list-group-item">
										<div class="media">
											<div class="media-left valign-middle">
												<i class="icon-check2 icon-bg-circle bg-green bg-accent-3"></i>
											</div>
											<div class="media-body">
												<h6 class="media-heading">Welcome!</h6>
												<p class="notification-text font-small-3 text-muted">Welcome to the Umbrella</p>
											</div>
										</div>
								</a></li>
								<li class="dropdown-menu-footer"><a
									href="javascript:void(0)"
									class="dropdown-item text-muted text-xs-center">Read all
										notifications</a></li>
							</ul></li>

						<li class="dropdown dropdown-user nav-item"><a href="#"
							data-toggle="dropdown"
							class="dropdown-toggle nav-link dropdown-user-link"><span
								class="avatar avatar-online"><img
									src="<%=request.getContextPath()%>/static/app-assets/images/portrait/small/avatar-s-1.png"
									alt="avatar"><i></i></span><span class="user-name">${fullname}</span></a>
							<div class="dropdown-menu dropdown-menu-right">
								<c:if test="${theme=='dark'}">
									<a href="/umbrella/theme/light" class="dropdown-item">
									<i class="icon-check2 icon-bg-circle bg-grey bg-accent-3"></i> Light Theme</a>
								</c:if>
								<c:if test="${theme=='light'}">
									<a href="/umbrella/theme/dark" class="dropdown-item">
									<i class="icon-check2 icon-bg-circle bg-black bg-accent-3"></i> Dark Theme</a>
								</c:if>
                  				<div class="dropdown-divider"></div>
                  					<c:forEach var="app" items="${appList}">
                  						<a href="/umbrella/menu/${app.id}" class="dropdown-item">
                  							<i class="${app.value} icon-bg-circle bg-blue bg-accent-3"></i> ${app.name}</a>
                  						<div class="dropdown-divider"></div>
                  					</c:forEach>
								<sform:form name="headerform" action="/umbrella/perform_logout"
									method="POST">
									<a href="#" onclick="logout()" class="dropdown-item"><i
										class="icon-power3"></i> Logout</a>
								</sform:form>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	
	<script type="text/javascript">
		function logout() {
			document.forms['headerform'].submit();
		}
	</script>
	
	<!-- BEGIN VENDOR JS-->
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/libraries/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/tether.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/libraries/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/perfect-scrollbar.jquery.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/unison.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/blockUI.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/jquery.matchHeight-min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/screenfull.min.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/extensions/pace.min.js"
		type="text/javascript"></script>
	<!-- BEGIN VENDOR JS-->
	<!-- BEGIN PAGE VENDOR JS-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/app-assets/vendors/js/ui/prism.min.js"></script>
	<!-- END PAGE VENDOR JS-->
</body>
</html>