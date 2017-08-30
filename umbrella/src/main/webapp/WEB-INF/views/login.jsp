<!DOCTYPE html>

<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/app-assets/css/pages/login-register.css">
</head>
<body>
	<div class="app-content content container-fluid">
			<div class="content-body">
				<section class="flexbox-container">
					<div
						class="col-md-4 offset-md-4 col-xs-10 offset-xs-1  box-shadow-2 p-0">
						<div class="card border-grey border-lighten-3 m-0">
							<div class="card-header no-border">
								<div class="card-title text-xs-center">
									<div class="p-1">
										<img
											src="<%=request.getContextPath()%>/static/app-assets/images/logo/Umbrella-black-big.png"
											alt="branding logo">
									</div>
								</div>
								<h6
									class="card-subtitle line-on-side text-muted text-xs-center font-small-3 pt-2">
									<span>LOGIN TO UMBRELLA</span>
								</h6>
							</div>
							<div class="card-body collapse in">
								<div class="card-block">
									<sform:form name='f' action="/umbrella/login" method='POST'>
										<fieldset
											class="form-group position-relative has-icon-left mb-0">
											<input type="text"
												class="form-control form-control-lg input-lg" id="user-name"
												placeholder="Your Staff ID" name="username" required>
											<div class="form-control-position">
												<i class="icon-head"></i>
											</div>
										</fieldset>
										<fieldset class="form-group position-relative has-icon-left">
											<input type="password"
												class="form-control form-control-lg input-lg" name="password"
												id="user-password" placeholder="Enter Password" required>
											<div class="form-control-position">
												<i class="icon-key3"></i>
											</div>
										</fieldset>
										<fieldset class="form-group row">
											<div class="col-md-6 col-xs-12 text-xs-center text-md-left">
												<fieldset>
													<input type="checkbox" id="remember-me" name="remember-me"
														class="chk-remember"> <label for="remember-me">
														Remember Me</label>
												</fieldset>
											</div>
										</fieldset>
										<button type="submit" class="btn btn-info btn-lg btn-block">
											<i class="icon-unlock2"></i> Login
										</button>
									</sform:form>
								</div>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>
</body>

</html>