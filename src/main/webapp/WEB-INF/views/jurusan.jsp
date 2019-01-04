<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<html>
<head>
<title>Jurusan</title>

<%-- <link rel="stylesheet" href="${contextName}/assets/bootstrap/css/bootstrap.min.css">
<script src="${contextName}/assets/jquery/jquery-3.3.1.min.js"></script>
<script src="${contextName}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextName}/assets/js/default.js"></script> --%>
<style type="text/css">
	
	.input-jur {
		background-color: #1E90FF;
		padding : 15px;
		margin : 70px 50px;
		opacity: 0.7;
	  	filter: alpha(opacity=50);
	}
	
	.input-jur:hover {
	  opacity: 1.0;
	  filter: alpha(opacity=100); /* For IE8 and earlier */
	}
	body {
		background-image: url("https://png.pngtree.com/thumb_back/fw800/back_pic/04/48/50/00585a3568a0a7d.jpg");
	    background-size: cover;
	    background-attachment: fixed;
	    background-repeat: no-repeat;
	}
	.btn {
		margin-top: 10px;	
	}
	.data-mhs {
		padding : 15px;
		margin : 70px 10px;
	}
</style>
</head>
<body>
	<div class="row baris-1">
		<div class="col-5">
			<div class="input-jur">
				<h4 style="text-align: center">Form Data Jurusan</h4>
				<form id="form-mahasiswa">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="mode" name="mode">
				    <div class="form-group">
				      <label for="kdJurusan">Kode Jurusan:</label>
				      <input type="text" class="form-control" id="kdJurusan" name="kdJurusan" placeholder="Masukkan Kode Jurusan">
				    </div>
				    <div class="form-group">
				      <label for="nmJurusan">Nama Jurusan:</label>
				      <input type="text" class="form-control" id="nmJurusan" name="nmJurusan" placeholder="Masukkan Nama Jurusan">
				    </div>
				    <button type="submit" class="btn btn-success" name="submit" value="Simpan" onclick="simpanData()">Simpan</button>
			  	</form>
			</div>
			
		</div>
		<div class="col-7">
			<div class="data-jur">
				<h5> Cari jurusan </h5>
				<input type="search" placeholder="Cari" id="cari-jurusan" name="cari" onkeypress="cari(this.value)" style="width: 300px">
				<h5> Data Tersimpan </h5>
				<div id="jurusan-list">
				</div>
			</div>
		</div>
	</div>


<!--  	<form>
		<table>
			<tr>
				<td>NIM</td>
				<td><input type="text" value="" id="nim"></td>
			</tr>
			<tr>
				<td>Nama</td>
				<td><input type="text" value="" id="nama"></td>
			</tr>
			<tr>
				<td>Tanggal Lahir</td>
				<td><input type="text" value="" id="tanggalLahir"></td>
			</tr>
		</table>
	</form> -->
	
<script>
	function simpanData() {
		var tipePost;
		if ($('#mode').val() != 'edit') {
			$('#id').val('');
			tipePost='post';
		}
		else {
			tipePost = 'put';
		}
		
		var data = getFormData($('#form-mahasiswa'));
		$.ajax({
			url: '/tesbackend/jurusan/',
			type: tipePost,
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(d) {
				loadData();
				$('#mode').val('tambah');
			},
			error: function(d) {
				alert('Error');
			}
		});
	}

	function loadData() {
		$.ajax({
			url: '/tesbackend/jurusan/',
			type: 'get',
			success: function(d) {
				$('#jurusan-list').empty();
				var table = $("<table class='table' />");
				var thead = $("<thead/>");
				var tr = $("<tr/>");

				var th = $("<th/>");
				$(th).text("Kode Jurusan");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Nama Jurusan");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Ubah");
				$(tr).append(th);
				$(thead).append(tr);

				$(table).append(thead);
				
				$(d).each(function(index, element) {
					tr = $("<tr/>");
					var td = $("<td/>");
					$(td).text(element.kdJurusan);
					$(tr).append(td);
					
					td = $("<td/>");
					$(td).text(element.nmJurusan);
					$(tr).append(td);

					td = $("<td/>");
					$(td).html(" <a href='#' onclick='edit("+element.id+")' class='btn btn-secondary'> Edit </a> <a href='#' onclick='hapus("+element.id+")' class='btn btn-danger'> Hapus </a> ")
					$(tr).append(td);

					$(table).append(tr);
				});
				
				$('#jurusan-list').append(table);
			},
			error: function(d) {
			}
		});
	}
	
	function edit(id) {
		$.ajax({
			type: "get",
			url: '/tesbackend/jurusan/'+id,
			success: function(value) {
				$('#id').val(value.id);
				$('#kdJurusan').val(value.kdJurusan);
				$('#nmJurusan').val(value.nmJurusan);
				$('#mode').val('edit');
			},
			error: function(d) {
				alert("Error");
			}
		});
	}
	
	function hapus(id) {
		if (confirm('Anda yakin?')) {
			$.ajax({
				type: 'delete',
				url: '/tesbackend/jurusan/'+id,
				success: function(d) {
					loadData();
				}
			});
		}
	}
	
	function cari(nama) {
		$.ajax({
			url: '/tesbackend/jurusan/search/'+nama,
			type: 'get',
			success: function(d) {
				$('#jurusan-list').empty();
				var table = $("<table class='table'/>");
				var thead = $("<thead/>");
				var tr = $("<tr/>");

				var th = $("<th/>");
				$(th).text("Kode Jurusan");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Nama Jurusan");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Ubah");
				$(tr).append(th);
				$(thead).append(tr);

				$(table).append(thead);
				
				$(d).each(function(index, element) {
					tr = $("<tr/>");
					var td = $("<td/>");
					$(td).text(element.kdJurusan);
					$(tr).append(td);
					
					td = $("<td/>");
					$(td).text(element.nmJurusan);
					$(tr).append(td);

					td = $("<td/>");
					$(td).html(" <a href='#' onclick='edit("+element.id+")' class='btn btn-secondary'> Edit </a> <a href='#' onclick='hapus("+element.id+")' class='btn btn-danger'> Hapus </a> ");
					$(tr).append(td);

					$(table).append(tr);
				});
				
				$('#jurusan-list').append(table);
			},
			error: function(d) {
				loadData();
			}
		});
	}

	$(document).ready(function() {
		loadData();
	});
</script>

</body>

</html>