import { NavigationContainer } from "@react-navigation/native";
import { View } from "react-native";
import { navigationRef } from "./NavigationServices";
import { useTheme } from "@theme";

const MainNavigation = () => {
  const { NavigationTheme } = useTheme();
  return (
    <NavigationContainer ref={navigationRef} theme={NavigationTheme}>
      <View></View>
    </NavigationContainer>
  );
};

export default MainNavigation;
