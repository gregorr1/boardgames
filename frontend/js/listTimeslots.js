import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

createApp({
    data() {
        return {
            timeslots: [],
            barTables: [],
            timeslot: {
                id: '',
                dayInWeek: '',
                startTime: '',
                endTime: '',
                listPlayers: [],
                table: {
                    id: ''
                }
            },
            editMode: false            
        }
    },
    created() {
        this.loadTables();
        this.loadTimeslots();
    },
    methods: {
        loadTables() {
            axios.get("http://localhost:8080/getAllTables")
            .then((response) => {
                this.barTables = response.data;
            })
            .catch((error) => console.error(error));
        },
        loadTimeslots() {
            axios.get("http://localhost:8080/getAllTimeslots")
            .then((response) => {
                this.timeslots = response.data;
                this.timeslots.forEach(timeslot => {
                    this.loadPlayersByTimeslot(timeslot);
                });
            })
            .catch((error) => console.error(error));
        },
        loadPlayersByTimeslot(t) {
            axios.get("http://localhost:8080/getPlayersByTimeslotId/" + t.id)
            .then((response) => {
                t.listPlayers = response.data;
            })
            .catch((error) => console.error(error));
        },
        deleteTimeslot(id) {
            axios.delete("http://localhost:8080/deleteTimeslot/" + id)
            .then((response) => {
                this.loadTimeslots();
            })
            .catch((error) => console.error(error));
        },
        checkTimeslot() {
            const timeslotExists = this.timeslots.some(timeslot => timeslot.id == this.timeslot.id);
            if(!this.editMode && timeslotExists) {
                alert("Termin s tem ID-jem že obstaja.");
                return false;
            } else {
                return true;
            }
        },
        checkIfTimeslotValid() {
            if(this.timeslot.dayInWeek < 1 || this.timeslot.dayInWeek > 7) {
                alert("Število dneva mora biti med 1 in 7");
                return false;
            } else if (this.timeslot.startTime < 0 ||this.timeslot.startTime > 23) {
                alert("Začetni čas mora biti med 0 in 23.");
                return false;
            } else if (this.timeslot.endTime < 1 || this.timeslot.endTime > 24 || this.timeslot.endTime <= this.timeslot.startTime) {
                alert("Končni čas mora biti med 1 in 24 ter po začetnem.");
                return false;
            } else {
                return true;
            }
        },
        sendTimeslot() {
            if(this.checkTimeslot() && this.checkIfTimeslotValid()) {
                axios.post("http://localhost:8080/addOrUpdateTimeslot", this.timeslot)
                .then((response) => {
                    this.loadTimeslots();
                    this.timeslot.id = '';
                    this.timeslot.dayInWeek = '';
                    this.timeslot.startTime = '';
                    this.timeslot.endTime = '';
                    this.timeslot.table = {};
                    this.editMode = false;
                })
                .catch((error) => console.error(error));
            }
        },
        selectTimeslot(t) {
            this.timeslot.id = t.id;
            this.timeslot.dayInWeek = t.dayInWeek;
            this.timeslot.startTime = t.startTime;
            this.timeslot.endTime = t.endTime;
            this.timeslot.table = t.table;
            this.editMode = true;
        },
        getDayInWeek(day) {
            switch(day) {
                case 1:
                    return "ponedeljek";
                case 2:
                    return "torek";
                case 3:
                    return "sreda";
                case 4:
                    return "četrtek";
                case 5:
                    return "petek";
                case 6:
                    return "sobota";
                case 7:
                    return "nedelja";
                default:
                    return "n/a";
            }
        }    
    }
}).mount('#app')