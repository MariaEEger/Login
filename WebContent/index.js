var SENAI = {
		usuario: {}
};

$(document).ready(function(){
	SENAI.usuario.logar = function(){ 
		
	if(document.getElementById("login").value == "" ||	   
	   document.getElementById("senha").value == "")		  
	{
		alert("Todos os campos são obrigatórios de preenchimento!");
		document.getElementById("login").focus();
		return false;		
	}
			
	else{		
		$.ajax({
			type:"POST",
			url: "LoginUsuario",
			data: $("#logaUsuario").serialize(),
			success: function (msg){
			var cfg = {
					title: "Mensagem",
					heigth: 250,
					width: 400,					
					modal: true,
					buttons: {
								"OK": function() {
								window.location.href = msg.redic;
								$(this).dialog(
								"close");
								}
							}					
			};			
			$("#msg").html(msg.msg);
			$("#msg").dialog(cfg);
											
			},
			error: function (rest){
			alert("Erro ao logar usuario");
			}
		});
	}	
};
});