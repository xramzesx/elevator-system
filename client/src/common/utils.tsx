import constants from "./constants";
import { ElevatorRequest } from "./types";

export const getRandomInt = (min:number, max:number)  => {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1) + min)
}

export const pickup = async (simulationId: string, request: ElevatorRequest) => {
    const response = await fetch(
        new URL(`simulation/${simulationId}/pickup`, constants.api.url),
        {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body : JSON.stringify(request)
        }
    );
    
    if (!response.ok)
        throw new Error(`Code: ${response.status}`)
}