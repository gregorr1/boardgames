import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

createApp({
    data() {
        return {
            barTables: [],
            searchedBarTables: [],
            searchWord: '',
            barTable: {
                id: '',
                boardGame: '',
                barName: '',
            },
            editMode: false            
        }
    },
    created() {
        this.loadTables();
    },
    methods: {
        loadTables() {
            axios.get("http://localhost:8080/getAllTables")
            .then((response) => {
                this.barTables = response.data;
            })
            .catch((error) => console.error(error));
            console.log("HI");
        },
        deleteTable(id) {
            axios.delete("http://localhost:8080/deleteTable/" + id)
            .then((response) => {
                this.loadTables();
            })
            .catch((error) => console.error(error));
        },
        checkTable() {
            const tableExists = this.barTables.some(table => table.id == this.barTable.id);
            if(!this.editMode && tableExists) {
                return false;
            } else {
                return true;
            }
        },
        sendTable() {
            if(this.checkTable()) {
                axios.post("http://localhost:8080/addOrUpdateTable", this.barTable)
                .then((response) => {
                    this.loadTables();
                    this.barTable.id = '';
                    this.barTable.boardGame = '';
                    this.barTable.barName = '';
                    this.editMode = false;
                })
                .catch((error) => console.error(error));
            } else {
                alert("Miza s tem ID-jem že obstaja.");
            }
        },
        selectTable(t) {
            this.barTable.id = t.id;
            this.barTable.boardGame = t.boardGame;
            this.barTable.barName = t.barName;
            this.editMode = true;
        },
        getTablesByGame(searchInput) {
            axios.get("http://localhost:8080/getTablesByBoardGame/" + searchInput)
            .then((response) => {
                this.searchedBarTables = response.data;
            })
            .catch((error) => console.error(error));
            console.log(this.searchedBarTables);
        }        
    }
}).mount('#app')