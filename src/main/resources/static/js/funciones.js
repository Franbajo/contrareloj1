/**
 * 
 */
function eliminar(id){
	console.log(id);
	swal({
		title: "Estás eguro de Eliminar?",
		text: "Una vez eliminado no se prodrá restablecer!",
		icon: "warning",
		  buttons: true,
		dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			$.ajax({
				url:"/eliminarClientes/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		swal("Poof! Registro eliminado!", {
		      icon: "success",
		    }).then((ok)=>{
			if(ok){
				location.href="/listarClientes";
			}
		    });
		  } 
		});

	
	
}