import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

createApp({
    data() {
        return {
            players: [],
            timeslots: [],
            player: {
                id: '',
                firstName: '',
                lastName: '',
                timeslot: {
                    id: ''
                }
            },
            editMode: false      
        }
    },
    created() {
        this.loadTimeslots();
        this.loadPlayers();
    },
    methods: {
        loadTimeslots() {
            axios.get("http://localhost:8080/getAllTimeslots")
            .then((response) => {
                this.timeslots = response.data;
            })
            .catch((error) => console.error(error));
        },
        loadPlayers() {
            axios.get("http://localhost:8080/getAllPlayers")
            .then((response) => {
                this.players = response.data;
            })
            .catch((error) => console.error(error));
        },
        deletePlayer(id) {
            axios.delete("http://localhost:8080/deletePlayer/" + id)
            .then((response) => {
                this.loadPlayers();
            })
            .catch((error) => console.error(error));
        },
        checkPlayer() {
            const playerExists = this.players.some(player => player.id == this.player.id);
            if(!this.editMode && playerExists) {
                return false;
            } else {
                return true;
            }
        },
        sendPlayer() {
            if(this.checkPlayer()) {
                axios.post("http://localhost:8080/addOrUpdatePlayer", this.player)
                .then((response) => {
                    this.loadPlayers();
                    this.player.id = '';
                    this.player.firstName = '';
                    this.player.lastName = '';
                    this.player.timeslot = {};
                    this.editMode = false;
                })
                .catch((error) => console.error(error));
            } else {
                alert("Igralec s tem ID-jem že obstaja.");
            }
        },
        selectPlayer(p) {
            this.player.id = p.id;
            this.player.firstName = p.firstName;
            this.player.lastName = p.lastName;
            this.player.timeslot = p.timeslot
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