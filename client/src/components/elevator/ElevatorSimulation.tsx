import { ElevatorRequest } from "../../common/types";
import { useMemo } from "react";
import ElevatorFloor from "./floor/ElevatorFloor";
import { styled } from "styled-components";
import { pickup } from "src/common/utils";
import ElevatorCarManager from "./car/ElevatorCarManager";
import useSimulationStatus from "src/hooks/useSimulationStatus";
import ElevatorDisplays from "./display/ElevatorDisplays";

interface IElevatorSimulation {
    lowestFloor: number;
    highestFloor: number;
    simulationId: string;
}

const ElevatorSimulation = (props : IElevatorSimulation) => {
    const { lowestFloor, highestFloor, simulationId } = props;

    const { data, isLoading, isError, error } = useSimulationStatus(simulationId)
    const statusList = data ?? []

    const handlePickup = (request: ElevatorRequest) => {
        console.log('request',request)
        pickup(simulationId, request)
    }

    const floors = useMemo(
        () => {
            const result = []

            for (let i = highestFloor; i >= lowestFloor; i--) {
                result.push((<
                    ElevatorFloor 
                    floor={i} 
                    statusList={statusList}
                    isHighest={i == highestFloor}
                    isLowest={i == lowestFloor}
                    onPickup={handlePickup}
                    key={i}
                />))
            }

            return result
        }, [lowestFloor, highestFloor, data]
    )

    return (
        <>
            <ElevatorDisplays
                statusList={statusList}
            />
            <ElevatorCarManager 
                statusList={statusList} 
                lowestFloor={lowestFloor} 
                highestFloor={highestFloor}                
            />
            {floors}
        </>
    )
}

export default ElevatorSimulation