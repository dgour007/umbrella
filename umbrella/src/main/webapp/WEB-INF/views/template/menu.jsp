<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

</head>
<body>
	<c:set var="tmenu" value="${menu}" scope="session" />
	<c:set var="activemenuid" value="${activemenu}" scope="session" />
	
	<div data-scroll-to-active="true"
		class="main-menu menu-fixed menu-${theme} menu-accordion menu-shadow">
		<div class="main-menu-header">
			<input type="text" placeholder="Search"
				class="menu-search form-control round"/>
		</div>
		<div class="main-menu-content">
			<ul id="main-menu-navigation" data-menu="menu-navigation" class="navigation navigation-main">
				<c:forEach items="${tmenu}" var="titleMenu">
					<li id="${titleMenu.menuId}" class=" nav-item">
						<a href="${titleMenu.action}">
							<i class="${titleMenu.iconClass}"></i>
							<span data-i18n="nav.${titleMenu.menuId}.main" class="menu-title">${titleMenu.menuName}</span>
						</a>
						
						<c:forEach var="menuLevel1" items="${titleMenu.childMenu}">
							<ul class="menu-content">
								<li id="${menuLevel1.menuId}" >
									<a href="${menuLevel1.action}" data-i18n="nav.${titleMenu.menuId}.second_level.${menuLevel1.menuId}.main" 
									class="menu-item">${menuLevel1.menuName}</a>
									
									<c:forEach var="menuLevel2" items="${menuLevel1.childMenu}">
										<ul class="menu-content">
											<li id="${menuLevel2.menuId}">
												<a href="${menuLevel2.action}" 
													data-i18n="nav.${titleMenu.menuId}.second_level.${menuLevel1.menuId}.third_level.${menuLevel2.menuId}.main" 
													class="menu-item">${menuLevel2.menuName}</a>
													
												<c:forEach var="menuLevel3" items="${menuLevel2.childMenu}">
													<ul class="menu-content">
														<li id="${menuLevel3.menuId}">
															<a  href="${menuLevel3.action}" 
																data-i18n="nav.${titleMenu.menuId}.second_level.${menuLevel1.menuId}.third_level.${menuLevel2.menuId}.fourth_level.${menuLevel3.menuId}" 
																class="menu-item">${menuLevel3.menuName}</a>
														</li>
													</ul>
												</c:forEach>
											</li>
										</ul>
									</c:forEach>
								</li>
							</ul>
						</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(
			function(){
		    	document.getElementById(${activemenuid}).classList.add('active');
			});
	</script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/app-menu.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/app.js"
		type="text/javascript"></script>

</body>
</html>