export class CreateRequest{
    constructor(variant){
        this.type = requestType.CREATE;
        this.variant = variant;
    }

    toString(){
        let msg = {
            type: this.type,
            variant: this.variant
        }

        return JSON.stringify(msg);
    }
}

export class JoinRequest{
    constructor(game_id){
        this.type = requestType.JOIN;
        this.game_id = game_id;
    }

    toString(){
        let msg = {
            type: this.type,
            game_id: this.game_id
        }

        return JSON.stringify(msg);
    }
}

export class MoveRequest{
    constructor(currentLocation,desiredLocation,game_id){
        this.type = requestType.MOVE;
        this.game_id = game_id;
        this.currentLocation = currentLocation;
        this.desiredLocation = desiredLocation;
    }

    toString(){
        let msg = {
            type: this.type,
            game_id: this.game_id,
            currentLocation: this.currentLocation,
            desiredLocation: this.desiredLocation
        }

        return JSON.stringify(msg);
    }
}

const requestType = {
    CREATE : "CREATE",
    JOIN : "JOIN",
    MOVE : "MOVE"
}