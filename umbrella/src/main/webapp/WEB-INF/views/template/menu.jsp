<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

</head>
<body>

	<!-- main menu-->
	<div data-scroll-to-active="true"
		class="main-menu menu-fixed menu-${theme} menu-accordion menu-shadow">
		<!-- main menu header-->
		<!-- <div class="main-menu-header">
			<input type="text" placeholder="Search"
				class="menu-search form-control round" />
		</div> -->
		<!-- / main menu header-->
		<!-- main menu content-->
		<div class="main-menu-content">
			<ul id="main-menu-navigation" data-menu="menu-navigation"
				class="navigation navigation-main">
				<li class="active"><a href="/umbrella/home"><i
						class="icon-home3"></i><span data-i18n="nav.home.main"
						class="menu-title">Home</span></a></li>
				<li class=" nav-item"><a href="#"><i
						class="icon-stats-dots"></i> <span data-i18n="nav.dash.main"
						class="menu-title">Dashboards</span><span
						class="tag tag tag-primary tag-pill float-xs-right mr-2">2</span></a>
					<ul class="menu-content">
						<li><a href="index.html" data-i18n="nav.dash.main"
							class="menu-item">Dashboard 1</a></li>
						<li><a href="dashboard-2.html" data-i18n="nav.dash.main"
							class="menu-item">Dashboard 2</a></li>
					</ul></li>
				<li class=" nav-item"><a href="#"><i class="icon-table2"></i><span
						data-i18n="nav.reports.main" class="menu-title">Reports</span></a>
					<ul class="menu-content">
						<li><a href="#" data-i18n="nav.reports.second_level"
							class="menu-item">Sales</a></li>
						<li><a href="#"
							data-i18n="nav.reports.second_level_child.main" class="menu-item">Fixed
								Inventory</a>
							<ul class="menu-content">
								<li><a href="#"
									data-i18n="nav.reports.second_level_child.third_level1"
									class="menu-item">Stock Balance Report</a></li>
								<li><a href="#"
									data-i18n="nav.reports.second_level_child.third_level2"
									class="menu-item">Stock Distribution Report</a></li>
							</ul></li>
					</ul></li>
			</ul>
		</div>
		<!-- /main menu content-->
		<!-- main menu footer-->
		<!-- include includes/menu-footer-->
		<!-- main menu footer-->
	</div>
	<!-- / main menu-->

	<!-- BEGIN ROBUST JS-->
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/app-menu.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/static/app-assets/js/core/app.js"
		type="text/javascript"></script>
	<!-- END ROBUST JS-->
	<!-- BEGIN PAGE LEVEL JS-->
	<!-- END PAGE LEVEL JS-->
</body>
</html>