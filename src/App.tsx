import {SafeAreaProvider} from 'react-native-safe-area-context';
import {Block, Text} from './components';
import {
  Dimensions,
  NativeModules,
  StyleProp,
  StyleSheet,
  TouchableOpacity,
  View,
  ViewStyle,
  requireNativeComponent,
} from 'react-native';
import {useTheme} from './theme';
import {NavigationContainer} from '@react-navigation/native';
import {useEffect, useState} from 'react';
interface ExoPlayerViewProps {
  style?: StyleProp<ViewStyle>;
  linkVideo?: string;
  onDidScanCard?: (event: any) => void;
}
const ExoPlayer = requireNativeComponent<ExoPlayerViewProps>('ExoPlayer');

const App = () => {
  const {NavigationTheme} = useTheme();

  const [count, setCount] = useState(0);

  useEffect(() => {}, []);

  console.log('ExoPlayer', ExoPlayer);

  const increment = () => {
    NativeModules.Counter.increment((value: any) => {
      setCount(value);
    });
  };

  const decrement = async () => {
    try {
      var result = await NativeModules.Counter.decrement();
      setCount(result);
    } catch (e: any) {
      console.log(e.message, e.code);
    }
  };

  return (
    <NavigationContainer theme={NavigationTheme}>
      <SafeAreaProvider>
        <ExoPlayer
          linkVideo={
            'https://cdn-video.hanet.ai/hanet-camera-vn/video/upload/C22035C050/2023/08/21/2023-08-21-13-43-27.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=RWAMQB0V2PBI0C2AEUAB%2F20230821%2F%2Fs3%2Faws4_request&X-Amz-Date=20230821T071436Z&X-Amz-Expires=900&X-Amz-SignedHeaders=host&x-id=GetObject&X-Amz-Signature=eb9f5580f72f0d933705d8670864fa11154a042758244ab9ec93f975d6d92d64'
          }
          style={{
            height: (Dimensions.get('window').width * 9) / 16 + 50,
            width: Dimensions.get('window').width,
            backgroundColor: 'red',
          }}
          onDidScanCard={(e: any) => console.log('==', e.nativeEvent)}
        />
      </SafeAreaProvider>
    </NavigationContainer>
  );
};

export default App;

const styles = StyleSheet.create({
  buttonContainer: {
    height: 40,
    width: 100,
    backgroundColor: 'red',
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 20,
  },
});
