function deleteStudents() {
	var items = $("input[type=checkbox]:checked");
	if (items.length == 0) {
		alert("Please select students");
		return;
	}
	var ids = "";
	for ( var i = 0; i < items.length; i++) {
		ids += $(items[i]).attr("id");
		if (i < items.length - 1) {
			ids += ",";
		}
	}
	console.log(ids);
	console.log("ids=" + ids);
	var form = '<form id="deleteStudentForm" action="'
			+ context
			+ '/admin/studentsList.php" method="post"><input type="hidden" name="ids" /></form>';
	$("body").append(form);
	$('#deleteStudentForm input').val(ids);
	$('#deleteStudentForm').submit();

}

function progressStudents() {
	var items = $("input[type=checkbox]:checked");
	if (items.length == 0) {
		alert("Please select student");
		return;
	}
	if (items.length > 1) {
		alert("Please select only one student");
		return;
	}
	var id = $(items).attr("id");
	console.log(id);
	console.log("id=" + id);
	var form = '<form id="progressStudentsForm" action="'
			+ context
			+ '/studentProgress.php" method="get"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#progressStudentsForm input').val(id);
	$('#progressStudentsForm').submit();
}

function modifyingStudent() {
	var item = $("input[type=checkbox]:checked");
	if (item.length == 0) {
		alert("Please select student");
		return;
	}
	if (item.length > 1) {
		alert("Please select only one student");
		return;
	}
	var id = $(item).attr("id");
	console.log(id);
	console.log("id=" + id);
	var form = '<form id="modifyingStudentForm" action="'
			+ context
			+ '/admin/studentsModifying.php" method="get"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#modifyingStudentForm input').val(id);
	$('#modifyingStudentForm').submit();

}

function toggle(chkBox) {
	checkboxes = document.getElementsByTagName("input");
	var isChecked = chkBox.checked;
	for ( var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked = false;
	}
	if (isChecked) {
		chkBox.checked = true;
	}
}

function deleteDiscipline() {
	var item = $("input[type=checkbox]:checked");
	if (item.length == 0) {
		alert("Please select discipline");
		return;
	}

	var id = $(item).attr("id");
	console.log(id);
	console.log("id=" + id);
	var form = '<form id="deleteDisciplineForm" action="'
			+ context
			+ '/admin/disciplinesList.php" method="post"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#deleteDisciplineForm input').val(id);
	$('#deleteDisciplineForm').submit();

}

function modifyingDiscipline() {
	var item = $("input[type=checkbox]:checked");
	if (item.length == 0) {
		alert("Please select discipline");
		return;
	}

	var id = $(item).attr("id");
	console.log(id);
	console.log("id=" + id);
	var form = '<form id="modifyingDisciplineForm" action="'
			+ context
			+ '/admin/disciplinesModifying.php" method="get"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#modifyingDisciplineForm input').val(id);
	$('#modifyingDisciplineForm').submit();
}

function createDiscipline() {

	var form = '<form id="createDisciplineForm" action="' + context
			+ '/admin/disciplinesCreating.php" method="get"></form>';
	$("body").append(form);
	$('#createDisciplineForm').submit();

}

function modifyingTerm() {
	var id = document.getElementById("opening_list").value;

	console.log(id);
	console.log("id =" + id);
	var form = '<form id="termModifyingForm" action="'
			+ context
			+ '/admin/termModifying.php" method="get"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#termModifyingForm input').val(id);
	$('#termModifyingForm').submit();

}

function deleteTerm() {
	var id = document.getElementById("opening_list").value;

	console.log(id);
	console.log("id =" + id);
	var form = '<form id="termDeleteForm" action="'
			+ context
			+ '/admin/termsList.php" method="post"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#termDeleteForm input').val(id);
	$('#termDeleteForm').submit();

}

function chooseStudentTermMarks() {
	var idStd = document.getElementByName("chooserStd").value;
	var idTerm = document.getElementsByName("chooserTerm").value;
	
	alert("Please select discipline");
	console.log(idStd);
	console.log(idTerm);
	console.log("idStd =" + id);
	console.log("idTerm =" + idTerm);
	var form = '<form id="chooseStudentTermMarksForm" action="'
			+ context
			+ '/admin/termsList.php" method="post"><input type="hidden" name="id" /></form>';
	$("body").append(form);
	$('#chooseStudentTermMarksForm input').val(id);
	$('#chooseStudentTermMarksForm').submit();

}
