//creando inicio flow
function createInitPorcess() {
    console.log("entered createInitPorcess::..");
    var node = { key: "" , color:"white"};

    //validando title node
    var title = document.getElementById("titleText").value;
    console.log("titile: " + title);
    if (title == undefined || title == '') {
        alert("Error insert tittle..");
        return;
    }
    //seteando titulo
    node.key = title;
    document.getElementById("titleText").value = "";

    var linkDataArray = [];

    myDiagram.model.nodeDataArray.push(node);
    nodeDataArray = myDiagram.model.nodeDataArray;
    updateEvent(nodeDataArray, linkDataArray);
}

//create task
function createTaskProcess(){
    console.log("entering in createTaskProcess");

    var node = { key: "" , color:"white"};
    var arrow = { from: "", to:""}

    //validando title node
    var title = document.getElementById("titleText").value;
    console.log("titile: " + title);
    if (title == undefined || title == '') {
        alert("Error insert tittle..");
        return;
    }
    node.key = title;

    //validae selection
    var next = validateSelectedNode();
    console.log("validation: "+ next)
    if(next){
        alert("Error selected node..");
        return;
    }
    var it = myDiagram.selection.iterator;
    while (it.next()) {
    var node = it.value;
    console.log("it: " + node.key);
     arrow.from = node.key;
    }
    arrow.to=title;

    myDiagram.model.nodeDataArray.push(node);
    myDiagram.model.linkDataArray.push(arrow);
    var nodeDataArray= myDiagram.model.nodeDataArray;
    var linkDataArray = myDiagram.model.linkDataArray;
    updateEvent(nodeDataArray, linkDataArray);

}