<!DOCTYPE html>

<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/assets/css/bootstrap-multiselect.css">
</head>
<body>
	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-body">
				<section id="basic-form-layouts">
					<div class="row match-height">
						<div class="col-md-12">
							<div id="searchcard" class="card">
								<div class="card-header">
									<h4 class="card-title" id="basic-layout-form">
										<i class="icon-clipboard4"></i> Search
									</h4>
									<div class="heading-elements">
										<ul class="list-inline mb-0">
											<li><a data-action="collapse"><i class="icon-minus4"></i></a></li>
										</ul>
									</div>
								</div>
								<div class="card-body collapse in">
									<div class="card-block">
										<sform:form class="form" modelAttribute="agencyInvForm"
											action="/umbrella/agency/inv/summarydata" method='POST'>
											<sform:hidden path="itemList"/>
											<div class="form-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<spring:message code="label.item" />
															<select multiple="multiple" name="itemCodes"
																class="multiselect-ui form-control">
																<c:forEach items="${agencyInvForm.itemList}" var="item">
																	<option value="${item}">${item}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<spring:message code="label.counter" />
															<select multiple="multiple" name="counterIds"
																class="multiselect-ui form-control">
																<c:forEach items="${agencyInvForm.counterList}"
																	var="counter">
																	<option value="${counter.name}">${counter.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<spring:message code="label.agency" />
															<select multiple="multiple" name="agencyIds"
																class="multiselect-ui form-control">
																<c:forEach items="${agencyInvForm.agencyList}"
																	var="agency">
																	<option value="${agency.id}">${agency.name}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<spring:message code="label.start.date" />
															<input type="date" class="form-control border-info"
																name="startDate">
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<spring:message code="label.end.date" />
															<input type="date" class="form-control border-info"
																name="endDate">
														</div>
													</div>
												</div>
												<br>
												<div class="float-xs-right">
													<button type="button" class="btn btn-orange mr-1">
														<i class="icon-cross2"></i> Reset
													</button>
													<button type="submit" class="btn btn-info">
														<i class="icon-search"></i> Search
													</button>
												</div>
										</sform:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<c:if test="${agencyInvForm.agencyInvSummary!=null}">
					<div class="row match-height">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title" id="basic-layout-form">
										<i class="icon-clipboard4"></i> Data
									</h4>
									<div class="heading-elements">
										<ul class="list-inline mb-0">
											<li><a data-action="collapse"><i class="icon-minus4"></i></a></li>
											<li><a data-action="expand"><i class="icon-expand2"></i></a></li>
										</ul>
									</div>
								</div>
								<div class="card-body collapse2 in">
									<div class="card-block">
										<div class="table-responsive">
											<table class="table table-striped table-hover mb-0">
												<thead class="bg-blue">
													<tr class="white">
														<th>Item Code</th>
														<th>Item ID</th>
														<th>Counter</th>
														<th>Agency</th>
														<th>Trans</th>
														<th>Description</th>
														<th>Quantity</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${agencyInvForm.agencyInvSummary}"
														var="summary">
														<tr>
															<td>${summary.itemCode}</td>
															<td>${summary.itemId}</td>
															<td>${summary.counterId}</td>
															<td>${summary.agencyDesc}</td>
															<td>${summary.transType}</td>
															<td>${summary.transDesc}</td>
															<td>${summary.quantity}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<script type="text/javascript">
						$(function() {
							$("#searchcard").children('.card-body').collapse('toggle');
            				$("#searchcard").find('[data-action="collapse"] i').toggleClass('icon-minus4 icon-plus4');
						});
					</script>
				</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('.multiselect-ui').multiselect({
				includeSelectAllOption : true
			});
		});
	</script>
	<script
		src="<%=request.getContextPath()%>/static/assets/js/bootstrap-multiselect.js"
		type="text/javascript"></script>
</body>
</html>