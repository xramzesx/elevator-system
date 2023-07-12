import constants from "src/common/constants";
import { useQuery } from "react-query";
import { useParams } from "react-router-dom"
import ElevatorSimulation from "src/components/elevator/ElevatorSimulation"
import useSimulationConfig from "src/hooks/useSimulationConfig";
import { SimulationConfig } from "@common/types";

const SimulationPage = () => {
    const { simulationId } = useParams<{simulationId: string}>()

    const { data, isLoading, isError, error } = useSimulationConfig(simulationId ?? "")
        
    /// TODO: make better loading page and error page
    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (isError) {
        return <div>Error: {error?.message}</div>;
    }

    return (
        <>
            <ElevatorSimulation
                simulationId={simulationId ?? ""}
                lowestFloor={data!.lowestFloor}
                highestFloor={data!.highestFloor}
            />
        </>
    )
}

export default SimulationPage