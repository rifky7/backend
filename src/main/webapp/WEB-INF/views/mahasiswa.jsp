<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<html>
<head>
<title>Mahasiswa</title>
<script>
function test() {
	
}
</script>
<%-- <link rel="stylesheet" href="${contextName}/assets/bootstrap/css/bootstrap.min.css">
<script src="${contextName}/assets/jquery/jquery-3.3.1.min.js"></script>
<script src="${contextName}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${contextName}/assets/js/default.js"></script> --%>
<style type="text/css">
	
	.input-mhs {
		background-color: #1E90FF;
		padding : 15px;
		margin : 70px 50px;
		opacity: 0.7;
	  	filter: alpha(opacity=50);
	}
	
	.input-mhs:hover {
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
			<div class="input-mhs">
				<h4 style="text-align: center">Form Data Mahasiswa</h4>
				<form id="form-mahasiswa">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="mode" name="mode">
				    <div class="form-group">
				      <label for="nim">NIM:</label>
				      <input type="text" class="form-control" id="nim" name="nim" placeholder="Masukkan NIM">
				    </div>
				    <div class="form-group">
				      <label for="nama">Nama:</label>
				      <input type="text" class="form-control" id="nama" name="nama" placeholder="Masukkan Nama">
				    </div>
				    <div class="form-group">
				      <label for="tglLahir">Tanggal lahir:</label>
				      <input type="text" class="form-control" id="tanggalLahir" name="tanggalLahir" placeholder="Masukkan Tanggal lahir: yyyy-mm-dd">
				    </div>
				    <div class="form-group">
				      <label for="alamat">Alamat:</label>
				      <input type="text" class="form-control" id="alamat" name="alamat" placeholder="Masukkan Alamat">
				    </div>
				    <button type="submit" class="btn btn-success" name="submit" value="Simpan" onclick="simpanData()">Simpan</button>
			  	</form>
			</div>
			
		</div>
		<div class="col-7">
			<div class="data-mhs">
				<h5> Cari mahasiswa </h5>
				<input type="search" placeholder="Cari" id="cari-mahasiswa" name="cari" onkeypress="cari(this.value)" style="width: 300px">
				<h5> Data Tersimpan </h5>
				<div id="mahasiswa-list">
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
			url: '/tesbackend/mahasiswa/',
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
			url: '/tesbackend/mahasiswa/',
			type: 'get',
			success: function(d) {
				$('#mahasiswa-list').empty();
				var table = $("<table class='table' />");
				var thead = $("<thead/>");
				var tr = $("<tr/>");

				var th = $("<th/>");
				$(th).text("NIM");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Nama");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Alamat");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Tanggal Lahir");
				$(tr).append(th);
				$(thead).append(tr);

				th = $("<th/>");
				$(th).text("Ubah");
				$(tr).append(th);
				$(thead).append(tr);

				$(table).append(thead);
				
				$(d).each(function(index, element) {
					tr = $("<tr/>");
					var td = $("<td/>");
					$(td).text(element.nim);
					$(tr).append(td);
					
					td = $("<td/>");
					$(td).text(element.nama);
					$(tr).append(td);

					td = $("<td/>");
					$(td).text(element.alamat);
					$(tr).append(td);

					td = $("<td/>");
					$(td).text(element.tanggalLahir);
					$(tr).append(td);

					td = $("<td/>");
					$(td).html(" <a href='#' onclick='edit("+element.id+")' class='btn btn-secondary'> Edit </a> <a href='#' onclick='hapus("+element.id+")' class='btn btn-danger'> Hapus </a> ")
					$(tr).append(td);

					$(table).append(tr);
				});
				
				$('#mahasiswa-list').append(table);
			},
			error: function(d) {
			}
		});
	}
	
	function edit(id) {
		$.ajax({
			type: "get",
			url: '/tesbackend/mahasiswa/'+id,
			success: function(value) {
				$('#id').val(value.id);
				$('#nim').val(value.nim);
				$('#nama').val(value.nama);
				$('#alamat').val(value.alamat);
				$('#tanggalLahir').val(value.tanggalLahir);
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
				url: '/data/mahasiswa/'+id,
				success: function(d) {
					loadData();
				}
			});
		}
	}
	
	function cari(nama) {
		$.ajax({
			url: '/tesbackend/mahasiswa/search/'+nama,
			type: 'get',
			success: function(d) {
				$('#mahasiswa-list').empty();
				var table = $("<table class='table'/>");
				var thead = $("<thead/>");
				var tr = $("<tr/>");

				var th = $("<th/>");
				$(th).text("NIM");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Nama");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Alamat");
				$(tr).append(th);

				th = $("<th/>");
				$(th).text("Tanggal Lahir");
				$(tr).append(th);
				$(thead).append(tr);

				th = $("<th/>");
				$(th).text("Ubah");
				$(tr).append(th);
				$(thead).append(tr);

				$(table).append(thead);
				
				$(d).each(function(index, element) {
					tr = $("<tr/>");
					var td = $("<td/>");
					$(td).text(element.nim);
					$(tr).append(td);
					
					td = $("<td/>");
					$(td).text(element.nama);
					$(tr).append(td);

					td = $("<td/>");
					$(td).text(element.alamat);
					$(tr).append(td);

					td = $("<td/>");
					$(td).text(element.tanggalLahir);
					$(tr).append(td);

					td = $("<td/>");
					$(td).html(" <a href='#' onclick='edit("+element.id+")' class='btn btn-secondary'> Edit </a> <a href='#' onclick='hapus("+element.id+")' class='btn btn-danger'> Hapus </a> ");
					$(tr).append(td);

					$(table).append(tr);
				});
				
				$('#mahasiswa-list').append(table);
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