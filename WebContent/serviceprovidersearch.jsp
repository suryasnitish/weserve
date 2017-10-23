<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PantryCup Home</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous" />
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js" />
</head>

<body>
	<br />
	<br />
	<br />
	<br />

	<div id="headersection" align="center">
		<table width="80%">
			<tr>
				<td width="30%"></td>
				<td>
					<h2 style="color:blue; font-weight=bold">Welcome Customer</h2>
				</td>
				<td align="right">
				<span> 
				<a href="/PantryCup/mybookings.jsp">My Bookings</a>
				</span>
				<span> 
				<a href="/PantryCup/spring/doLogout" class="btn btn-primary">Logout</a>
				</span>
				</td>
			</tr>
		</table>
	</div>

	<div id="mainbody"
		style='width: 80%; position: absolute; top: 200px; bottom: 0; left: 0; right: 0; margin: auto;'>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="0">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Service</th>
					<th>Cost</th>
					<th>Cost Basis</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Service</th>
					<th>Cost</th>
					<th>Cost Basis</th>
					<th>Actions</th>
				</tr>
			</tfoot>
		</table>

	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>

	<script src="/PantryCup/js/jquery/jquery-3.2.1.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var table = $('#example').DataTable({
				"ajax" : '/PantryCup/spring/search/serviceprovider',
				"columns" : [ {
					"data" : "no"
				}, {
					"data" : "fullname"
				}, {
					"data" : "specialization"
				}, {
					"data" : "cost"
				}, {
					"data" : "costBasis"
				}, {
					"data" : "action"
				} ],
				"columnDefs" : [ {
					"targets" : -1,
					"data" : null,
					"defaultContent" : "<button>Book</button>"
				}, {
					"targets" : [ 0 ],
					"visible" : false
				} ]
			});
			$('#example tbody').on('click', 'button', function() {
				var data = table.row($(this).parents('tr')).data();
				window.location = "/PantryCup/spring/startBooking?providerno=" + data.no;
			});

		});
	</script>
</body>
</html>