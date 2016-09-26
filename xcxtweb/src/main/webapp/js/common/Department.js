var Department = {
	"init" : function(hospitalDomId, departmentDomId, hospitalId, departmentId) {
		var hospitalDom = $("#" + hospitalDomId);
		var departmentDom = $("#" + departmentDomId);
		hospitalDom.change(function() {
			var hospitalId = hospitalDom.val();
			$.ajax({
				"url" : "/department/listByHospital.do",
				"data" : {
					"hospitalId" : hospitalId
				},
				"dataType" : "json",
				"async" : true,
				"success" : function(data) {
					departmentDom.empty();
					$.each(data.data, function(index, department) {
						departmentDom.append($("<option value='" + department.departmentId + "'>" + department.name
								+ "</option>"));
					});
					if (!!departmentId) {
						departmentDom.val(departmentId);
						departmentId = null;
					}
				},
				"error" : function() {
				},
				"type" : "POST"
			});
		});
		$.ajax({
			"url" : "/department/listAllHospital.do",
			"dataType" : "json",
			"async" : true,
			"success" : function(data) {
				hospitalDom.empty();
				$.each(data.data,
						function(index, hospital) {
							hospitalDom.append($("<option value='" + hospital.hospitalId + "'>" + hospital.name
									+ "</option>"));
						});
				if (!!hospitalId) {
					hospitalDom.val(hospitalId);
					hospitalId = null;
				}
				hospitalDom.trigger("change");
			},
			"error" : function() {
			},
			"type" : "POST"
		});

	},
	"end" : null
};