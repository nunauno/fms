<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><b>FMS</b></title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<style type="text/css">
td {
	text-align: 100px left;
}
</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container">
				<a class="navbar-brand" href="#">FMS</a>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item">
							<button type="button" class="btn btn-secondary"
								data-toggle="modal" data-target="#exampleModal1">
								Settings <i class="fas fa-cogs"></i>
							</button>
						</li>
						<li class="nav-item"><a class="nav-link disabled" href="#"></a>
						</li>
						<li class="nav-item"><a class="nav-link disabled" href="#"></a>
						</li>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<form class="form-inline my-2 my-lg-0" method="POST"
							action="/upload" enctype="multipart/form-data">
							<li class="nav-item">
								<div class="custom-file">
									<input type="file" name="fileContent" class="custom-file-input"
										id="customFile" /> <label class="custom-file-label"
										for="customFile">Choose file</label>
								</div>
							</li>
							<li class="nav-item"><i class="fas fa-times"></i></li>
							<li class="nav-item"><input type="submit"
								class="btn btn-outline-success" value="Upload" /></li>
						</form>
					</ul>

				</div>

			</div>
		</nav>
	</header>


	<!-- Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<b>Settings <i class="fas fa-cogs"></i></b>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="maxFileSize"><b>Max file size (MB)</b></label> <input
								type="text" class="form-control" id="maxFileSize"> <small
								id="" class="form-text text-muted"></small>
						</div>
						<div class="form-group">
							<label for="itemPerPage"><b>Item per page</b></label> <input
								type="text" class="form-control" id="itemPerPage">
						</div>
						<div class="form-group">
							<label for="exampleFormControlSelect1"><b>Allow
									upload type</b></label> <select class="form-control"
								id="exampleFormControlSelect1">
								<option>Image</option>
								<option>PDF</option>
								<option>Docs</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
				</form>
			</div>
		</div>
	</div>



	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<br>
				<h2>Files Management</h2>
				<hr>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">File Name</th>
							<th scope="col">Version</th>
							<th scope="col">File size (KB)</th>
							<th scope="col">Updated time</th>
							<th scope="col">Download</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fileDto.files}" var="file" varStatus="loop">
							<tr>
								<th scope="row" rowspan="1">${loop.index + 1}</th>				
								<td><a href="#">${file.name}</a></td>
								<td>${file.version }</td>
								<td>${file.fileSize }</td>
								<td>${file.updateDateTime }</td>
								<td style="text-align: center;">${file.numberOfDownload }</td>
								<td><a
									href="${pageContext.request.contextPath}/download/${file.id}">
										<i class="fas fa-download"></i>
								</a> | <a data-toggle="modal" data-target="#myModal_${file.id}"
									href="#"><i class="fas fa-trash-alt"></i></a> <!-- Modal --></td>
								
							</tr>
							

							<!-- Modal -->
							<div class="modal fade" id="myModal_${file.id}" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Delete
												file</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">Do you want to delete this file
											?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">No</button>
											<a
												href="${pageContext.request.contextPath}/delete?id=${file.id}"
												title="Delete" class="btn btn-primary">Delete</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</tbody>
				</table>
				<div class="d-flex">
					<div>
						<p style="color: gray;">page ${fileDto.currentPage +1} of
							${fileDto.totalPage + 1}</p>
					</div>
					<div class="ml-auto">

						<nav aria-label="Page navigation example">
							<ul class="pagination" style="text-align: left;">
								<c:choose>
									<c:when test="${fileDto.currentPage != 0}">
										<li class="page-item"><a class="page-link"
											href="?pageNo=${fileDto.currentPage - 1}"
											aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Previous</span>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link disabled"
											href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Previous</span>
										</a></li>
									</c:otherwise>
								</c:choose>

								<!-- list file -->
								<c:forEach begin="0" end="${fileDto.totalPage}" var="i">
									<li class="page-item"><a class="page-link"
										href="?pageNo=${i}">${i+1}</a></li>
								</c:forEach>
								<!-- NEXT -->
								<c:choose>
									<c:when test="${fileDto.currentPage lt fileDto.totalPage}">
										<li class="page-item"><a class="page-link disabled"
											href="?pageNo=${fileDto.currentPage + 1}" aria-label="Next">
												<span aria-hidden="true">&raquo;</span> <span
												class="sr-only">Next</span>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link disabled"
											href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Next</span>
										</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-1"></div>
	</div>
	<!-- End Row -->
	</div>
	<!-- End Container -->

	<script>
		// Add the following code if you want the name of the file appear on select
		$(".custom-file-input").on(
				"change",
				function() {
					var fileName = $(this).val().split("\\").pop();
					$(this).siblings(".custom-file-label").addClass("selected")
							.html(fileName);
				});
	</script>
</body>
</html>